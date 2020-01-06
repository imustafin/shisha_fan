import sys
import re

text = sys.stdin.read()

if not text:
    sys.exit()

if text.startswith('.so'):
    sys.exit()

def sub(p, r, flags=re.MULTILINE):
    global text
    text = re.sub(p, r, text, flags=re.MULTILINE)

sub(r"^\.\\\".*$", '')
sub(r"^\.\w\w.*$", '')
sub(r"^\.\w ", '')
sub(r"\\f.", '')
sub(r" +", ' ')

text = re.sub(r'\n\s*\n', '\n\n', text)
text = re.sub(r'^\n*', '', text)
text = re.sub(r'\\', '', text)
text = re.sub(r'(\w)\n(\w)', '\\1 \\2', text)
print(text)
