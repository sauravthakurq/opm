document.addEventListener('DOMContentLoaded', () => {
  const repo = 'sauravthakurq/OPM';
  const apiEndpoint = `https://api.github.com/repos/${repo}/releases/latest`;

  const loadingState = document.getElementById('loading-state');
  const successState = document.getElementById('success-state');
  const errorState = document.getElementById('error-state');

  const appVersion = document.getElementById('app-version');
  const apkSize = document.getElementById('apk-size');
  const publishDate = document.getElementById('publish-date');
  const downloadBtn = document.getElementById('download-btn');
  const releaseNotes = document.getElementById('release-notes');

  // Convert bytes to MB
  function formatBytes(bytes) {
    if (bytes === 0) return '0 Bytes';
    const k = 1024;
    const sizes = ['Bytes', 'KB', 'MB', 'GB'];
    const i = Math.floor(Math.log(bytes) / Math.log(k));
    return parseFloat((bytes / Math.pow(k, i)).toFixed(1)) + ' ' + sizes[i];
  }

  // Format date nicely (e.g. "Oct 12, 2024")
  function formatDate(isoString) {
    const options = { year: 'numeric', month: 'short', day: 'numeric' };
    return new Date(isoString).toLocaleDateString(undefined, options);
  }

  // Basic Markdown to HTML converter for release notes
  function parseMarkdown(md) {
    let html = md.replace(/^### (.*$)/gim, '<strong>$1</strong><br>');
    html = html.replace(/^## (.*$)/gim, '<strong>$1</strong><br>');
    html = html.replace(/^# (.*$)/gim, '<strong>$1</strong><br>');
    html = html.replace(/^\* (.*$)/gim, '<li>$1</li>');
    html = html.replace(/^- (.*$)/gim, '<li>$1</li>');
    html = html.replace(/\[([^\]]+)\]\(([^)]+)\)/gim, '<a href="$2" target="_blank">$1</a>');
    
    // Wrap lists
    html = html.replace(/(<li>.*<\/li>)/gim, '<ul>$1</ul>');
    // Remove consecutive ul tags
    html = html.replace(/<\/ul>\n<ul>/gim, '');
    
    // Newlines
    html = html.replace(/\n/gim, '<br>');
    
    return html;
  }

  async function fetchLatestRelease() {
    try {
      const response = await fetch(apiEndpoint);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      
      const data = await response.json();
      
      // Find the APK asset
      const apkAsset = data.assets.find(asset => asset.name.endsWith('.apk'));
      
      if (data && data.tag_name) {
        // Populate UI
        appVersion.textContent = data.tag_name;
        publishDate.textContent = formatDate(data.published_at);
        
        if (apkAsset) {
          apkSize.textContent = formatBytes(apkAsset.size);
          downloadBtn.href = apkAsset.browser_download_url;
        } else {
          // Fallback if no direct APK asset is attached yet
          apkSize.textContent = "N/A";
          downloadBtn.href = data.html_url;
        }

        if (data.body) {
          releaseNotes.innerHTML = parseMarkdown(data.body);
        } else {
          releaseNotes.innerHTML = "<p>No release notes provided.</p>";
        }

        // Show Success
        loadingState.classList.add('hidden');
        successState.classList.remove('hidden');
      } else {
        throw new Error('Invalid data format');
      }
      
    } catch (error) {
      console.error('Error fetching release:', error);
      // Show Error Fallback
      loadingState.classList.add('hidden');
      errorState.classList.remove('hidden');
    }
  }

  fetchLatestRelease();
});
