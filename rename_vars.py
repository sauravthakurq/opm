import os
import re

directories_to_search = [
    'app/src/main/kotlin'
]

replacements = [
    (r'echomusic_AUTO_SCROLL_DURATION', r'opm_AUTO_SCROLL_DURATION'),
    (r'echomusic_INITIAL_SCROLL_DURATION', r'opm_INITIAL_SCROLL_DURATION'),
    (r'echomusic_SEEK_DURATION', r'opm_SEEK_DURATION'),
    (r'echomusic_FAST_SEEK_DURATION', r'opm_FAST_SEEK_DURATION'),
    (r'echomusicLyricsLine', r'opmLyricsLine'),
    (r'TAG = "echomusic_', r'TAG = "opm_'),
    (r'"echomusic:ListenTogether"', r'"opm:ListenTogether"'),
    (r'Environment\.DIRECTORY_PICTURES \+ "/echomusic"', r'Environment.DIRECTORY_PICTURES + "/opm"'),
    (r'User-Agent", "Echo-Music/1\.0"', r'User-Agent", "OPM/1.0"'),
]

for directory in directories_to_search:
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.kt') or file.endswith('.xml'):
                filepath = os.path.join(root, file)
                try:
                    with open(filepath, 'r', encoding='utf-8') as f:
                        content = f.read()
                    
                    new_content = content
                    for old_pat, new_pat in replacements:
                        new_content = re.sub(old_pat, new_pat, new_content)
                    
                    if new_content != content:
                        with open(filepath, 'w', encoding='utf-8') as f:
                            f.write(new_content)
                        print(f"Updated {filepath}")
                except Exception as e:
                    print(f"Failed {filepath}: {e}")
