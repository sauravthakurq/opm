@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package sauravthakur.opm.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import sauravthakur.opm.BuildConfig
import sauravthakur.opm.LocalPlayerAwareWindowInsets
import sauravthakur.opm.R
import sauravthakur.opm.ui.component.IconButton
import sauravthakur.opm.ui.utils.backToMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
    onBack: (() -> Unit)? = null,
    highlightKey: String? = null,
) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = Color.Black,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "About OPM",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack?.invoke() ?: navController.navigateUp() },
                        onLongClick = navController::backToMain,
                    ) {
                        Icon(painterResource(R.drawable.arrow_back), contentDescription = null)
                    }
                },
                windowInsets = TopAppBarDefaults.windowInsets,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Black,
                    scrolledContainerColor = Color.Black,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(
                    LocalPlayerAwareWindowInsets.current.only(WindowInsetsSides.Horizontal),
                ),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                end = 16.dp,
                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() + 32.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item { AboutBanner() }

            item {
                AboutTextSection(
                    title = "About OPM",
                    paragraphs = listOf(
                        "A premium music player designed to deliver a clean, fast, and immersive listening experience.",
                        "Built with a focus on beautiful design, smooth performance, and a distraction-free interface, OPM combines modern visuals with powerful playback features to make enjoying music effortless.",
                        "Whether you're discovering new tracks, listening offline, reading synchronized lyrics, or managing your personal library, OPM is designed to keep the experience simple, elegant, and reliable.",
                    ),
                )
            }
            item {
                AboutBulletSection(
                    title = "Features",
                    bullets = listOf(
                        "Full-length music playback",
                        "High-quality audio streaming",
                        "Smart recommendations",
                        "Powerful search",
                        "Offline downloads",
                        "Synced lyrics",
                        "Background playback",
                        "Queue management",
                        "Modern premium interface",
                        "Lightweight & fast performance",
                    ),
                )
            }
            item {
                AboutBulletSection(
                    title = "Technology",
                    intro = "Built with modern Android technologies including:",
                    bullets = listOf(
                        "Kotlin",
                        "Jetpack Compose",
                        "Media3 ExoPlayer",
                        "Material Design",
                        "Innertube Streaming",
                        "Coroutines & Flow",
                    ),
                )
            }
            item {
                AboutTextSection(
                    title = "Designed & Developed By",
                    paragraphs = listOf(
                        "Saurav Thakur",
                        "Independent Android Developer focused on creating modern, high-performance applications with premium user experiences.",
                    ),
                )
            }
            item {
                AboutLinkSection(
                    title = "Connect",
                    links = listOf(
                        AboutLink(painterResource(R.drawable.email), "Email", "sauravthakur6310@gmail.com", "mailto:sauravthakur6310@gmail.com"),
                        AboutLink(painterResource(R.drawable.github), "GitHub", "https://github.com/sauravthakurq", "https://github.com/sauravthakurq"),
                        AboutLink(painterResource(R.drawable.ic_x_new), "X", "https://x.com/sauravthakurq", "https://x.com/sauravthakurq"),
                        AboutLink(painterResource(R.drawable.linkedin), "LinkedIn", "https://www.linkedin.com/in/sauravthakurq", "https://www.linkedin.com/in/sauravthakurq"),
                    ),
                    onOpen = uriHandler::openUri,
                )
            }
            item {
                AboutTextSection(
                    title = "Acknowledgements",
                    paragraphs = listOf(
                        "OPM's user interface and branding have been independently designed by Saurav Thakur.",
                        "The application is powered by open-source technologies that make modern Android development possible. We appreciate the open-source community and the developers whose work helps build better software.",
                    ),
                )
            }
            item {
                AboutTextSection(
                    title = "Copyright",
                    paragraphs = listOf(
                        "© 2026 Saurav Thakur",
                        "All Rights Reserved.",
                        "Made with ❤️",
                    ),
                )
            }
        }
    }
}

@Composable
private fun AboutBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .clip(RoundedCornerShape(30.dp)),
    ) {
        Image(
            painter = painterResource(R.drawable.aboutopmbanner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )

    }
}



@Composable
private fun AboutTextSection(title: String, paragraphs: List<String>) {
    AboutCard(title = title) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            paragraphs.forEach { paragraph ->
                Text(
                    text = paragraph,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (paragraph == "Saurav Thakur") Color.White else Color.White.copy(alpha = 0.72f),
                    fontWeight = if (paragraph == "Saurav Thakur") FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
private fun AboutBulletSection(title: String, intro: String? = null, bullets: List<String>) {
    AboutCard(title = title) {
        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            intro?.let {
                Text(it, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.72f))
                Spacer(modifier = Modifier.height(2.dp))
            }
            bullets.forEach { bullet ->
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
                    Text("•", color = Color.White, fontWeight = FontWeight.Bold)
                    Text(bullet, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.76f))
                }
            }
        }
    }
}

private data class AboutLink(
    val icon: Painter,
    val title: String,
    val subtitle: String,
    val url: String,
)

@Composable
private fun AboutLinkSection(title: String, links: List<AboutLink>, onOpen: (String) -> Unit) {
    AboutCard(title = title) {
        Column(modifier = Modifier.padding(vertical = 6.dp)) {
            links.forEachIndexed { index, link ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .clickable { onOpen(link.url) }
                        .padding(horizontal = 16.dp, vertical = 13.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Surface(modifier = Modifier.size(40.dp), shape = RoundedCornerShape(14.dp), color = Color.White.copy(alpha = 0.10f)) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            Icon(link.icon, contentDescription = null, modifier = Modifier.size(21.dp), tint = Color.White)
                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(link.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(link.subtitle, style = MaterialTheme.typography.bodySmall, color = Color.White.copy(alpha = 0.58f), maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
                if (index != links.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(start = 70.dp), color = Color.White.copy(alpha = 0.08f))
                }
            }
        }
    }
}

@Composable
private fun AboutCard(title: String? = null, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.055f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            content = { content() },
        )
    }
}
