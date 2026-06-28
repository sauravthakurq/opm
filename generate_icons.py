from PIL import Image, ImageOps
import os

# Load the source image
src_path = 'appiconorg.png'
img = Image.open(src_path).convert('RGBA')

mipmap_dirs = {
    'mdpi': 48,
    'hdpi': 72,
    'xhdpi': 96,
    'xxhdpi': 144,
    'xxxhdpi': 192
}

res_path = 'app/src/main/res'

# Generate standard launcher icons
for density, size in mipmap_dirs.items():
    resized = img.resize((size, size), Image.Resampling.LANCZOS)
    
    # Save as ic_launcher.png
    dir_path = os.path.join(res_path, f'mipmap-{density}')
    os.makedirs(dir_path, exist_ok=True)
    resized.save(os.path.join(dir_path, 'ic_launcher.png'))
    
    # Also save as ic_launcher_round.png (for now just square or rounded corner)
    # We'll just save it directly.
    resized.save(os.path.join(dir_path, 'ic_launcher_round.png'))
    
    # Also save as ic_launcher_foreground.png
    resized.save(os.path.join(dir_path, 'ic_launcher_foreground.png'))

print("Generated launcher icons.")

# Generate monochrome notification icon
# Convert to grayscale
gray = img.convert('L')
# We need to make it a transparent PNG where the logo is white.
# Assuming the logo is bright on a dark background or dark on a bright background.
# Let's check the corners to guess the background.
pixels = gray.load()
corner_color = pixels[0, 0]

# If corner is bright, we assume it's dark on light background. We invert it.
if corner_color > 127:
    gray = ImageOps.invert(gray)

# Use the gray image as the alpha channel, and solid white for the RGB.
white_img = Image.new('RGBA', img.size, (255, 255, 255, 255))
white_img.putalpha(gray)

# Notification icon sizes (24dp is standard, usually mdpi=24, hdpi=36, xhdpi=48, xxhdpi=72, xxxhdpi=96)
notif_dirs = {
    'mdpi': 24,
    'hdpi': 36,
    'xhdpi': 48,
    'xxhdpi': 72,
    'xxxhdpi': 96
}

for density, size in notif_dirs.items():
    resized_notif = white_img.resize((size, size), Image.Resampling.LANCZOS)
    dir_path = os.path.join(res_path, f'mipmap-{density}')
    os.makedirs(dir_path, exist_ok=True)
    resized_notif.save(os.path.join(dir_path, 'ic_launcher_monochrome.png'))
    resized_notif.save(os.path.join(dir_path, 'ic_notification.png'))
    # also check drawable
    drawable_dir = os.path.join(res_path, f'drawable-{density}')
    os.makedirs(drawable_dir, exist_ok=True)
    resized_notif.save(os.path.join(drawable_dir, 'ic_notification.png'))

print("Generated monochrome icons.")
