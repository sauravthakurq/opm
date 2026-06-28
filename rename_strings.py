import os
import re

directories_to_search = [
    'app/src/main/res',
    'app/src/main/kotlin'
]

replacements = [
    (r'name="echo_brain_', r'name="opm_brain_'),
    (r'name="echo_equalizer', r'name="opm_equalizer'),
    (r'name="echo_music_', r'name="opm_music_'),
    (r'name="echomusic_1"', r'name="opm_1"'),
    (r'name="echomusic_canvas', r'name="opm_canvas'),
    (r'name="eq_preset_echo_', r'name="eq_preset_opm_'),
    (r'name="eq_label_echo"', r'name="eq_label_opm"'),
    
    (r'R\.string\.echo_brain_', r'R.string.opm_brain_'),
    (r'R\.string\.echo_equalizer', r'R.string.opm_equalizer'),
    (r'R\.string\.echo_music_', r'R.string.opm_music_'),
    (r'R\.string\.echomusic_1', r'R.string.opm_1'),
    (r'R\.string\.echomusic_canvas', r'R.string.opm_canvas'),
    (r'R\.string\.eq_preset_echo_', r'R.string.eq_preset_opm_'),
    (r'R\.string\.eq_label_echo', r'R.string.eq_label_opm'),
    
    (r'>Echo Signature<', r'>OPM Signature<'),
    (r'>Echo<', r'>OPM<'),
    (r'>Echo Music<', r'>OPM<'),
    (r'>EchoMusic<', r'>OPM<'),
    
    # Widgets
    (r'Theme\.Widget\.echomusic', r'Theme.Widget.opm'),
    (r'Theme\.Widget\.OPM', r'Theme.Widget.opm'), # normalize
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
