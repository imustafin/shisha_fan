package com.project.shishafan.drivers;
/* Created by Kuyan Kirill
 *  Email: progingisfun@gmail.com
 *  Date: 01.11.2019
 */

import com.project.shishafan.drivers.models.Post;
import com.project.shishafan.services.VkService;
import com.project.shishafan.utils.PropertiesUtils;
import com.project.shishafan.utils.WriterUtils;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.enums.WallFilter;
import com.vk.api.sdk.objects.wall.responses.GetResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MainDriver extends BaseDriver {

    public MainDriver(String[] args) {
        super(args);
    }

    public void start() {

        int groupId;

        if(args.length < 2){
            System.err.println("Invalid number of arguments: " + args.length);
            System.err.println("Valid number of arguments: 2");
            System.exit(-1);
        }

        String cmd = args[0];
        String parameter = args[1];

        if(!cmd.equals("-i") && !cmd.equals("-n")){
            System.err.println("Invalid command argument: " + cmd);
            System.err.println("Possible command arguments: -i | -n");
            System.exit(-2);
        }

        VkService vkService = new VkService();

        if(cmd.equals("-n")){
            groupId = vkService.getGroupIdByName(parameter);
        }else{
            if(!parameter.matches("\\d+")){
                System.err.println("Invalid number: " + parameter);
                System.exit(-4);
            }
            groupId = Integer.parseInt(parameter);
        }

        if(groupId == 0){
            System.err.println("Invalid group id or group name");
            System.exit(-3);
        }

        List<Post> wallPosts = vkService.getAllPostsFromGroupWithPostsComments(groupId);
        WriterUtils.writeListAsJsonDataToFile(parameter, wallPosts);


    }
}
