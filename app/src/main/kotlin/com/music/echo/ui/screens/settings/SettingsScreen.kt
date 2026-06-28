

package sauravthakur.opm.ui.screens.settings

import sauravthakur.opm.R
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import sauravthakur.opm.BuildConfig
import sauravthakur.opm.LocalPlayerAwareWindowInsets
import sauravthakur.opm.ui.component.IconButton
import sauravthakur.opm.ui.component.Material3SettingsGroup
import sauravthakur.opm.ui.component.Material3SettingsItem
import sauravthakur.opm.ui.screens.Screens
import sauravthakur.opm.ui.utils.backToMain
import sauravthakur.opm.echomusic.updater.getUpdateAvailableState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
highlightKey: String? = null) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current
    val isAndroid12OrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val isUpdateAvailable = getUpdateAvailableState(context) && sauravthakur.opm.echomusic.updater.getAutoUpdateCheckSetting(context)

    var searchQuery by rememberSaveable { mutableStateOf("") }
    val searchLower = searchQuery.lowercase()

    val accountText = stringResource(R.string.account)
    val appearanceText = stringResource(R.string.appearance)
    val playerText = stringResource(R.string.player_and_audio)
    val listenTogetherText = stringResource(R.string.listen_together)
    val contentText = stringResource(R.string.content)
    val aiLyricsText = stringResource(R.string.ai_lyrics_translation)
    val privacyText = stringResource(R.string.privacy)
    val storageText = stringResource(R.string.storage)
    val backupText = stringResource(R.string.backup_restore)
    val systemUpdateText = stringResource(R.string.system_update)
    val aboutText = "About OPM"
    val developerText = "About Developer"

    val scrollState = rememberScrollState()
    Column(
        Modifier
            .background(Color.Black)
            .windowInsetsPadding(LocalPlayerAwareWindowInsets.current.only(WindowInsetsSides.Horizontal))
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(
            Modifier.windowInsetsPadding(
                LocalPlayerAwareWindowInsets.current.only(
                    WindowInsetsSides.Top
                )
            )
        )
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp, top = 24.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(stringResource(R.string.search)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.search)
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            shape = RoundedCornerShape(24.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White.copy(alpha = 0.08f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
                focusedBorderColor = Color.White.copy(alpha = 0.40f),
                unfocusedBorderColor = Color.White.copy(alpha = 0.14f),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedPlaceholderColor = Color.White.copy(alpha = 0.50f),
                unfocusedPlaceholderColor = Color.White.copy(alpha = 0.42f),
                focusedLeadingIconColor = Color.White,
                unfocusedLeadingIconColor = Color.White.copy(alpha = 0.72f),
                focusedTrailingIconColor = Color.White,
                unfocusedTrailingIconColor = Color.White.copy(alpha = 0.72f),
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
        )

        val itemsList = buildList {

            if ("opm brain".contains(searchLower) || "echo brain".contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == "OPM Brain (Beta)"),
                        icon = rememberVectorPainter(Icons.Outlined.AutoAwesome),
                        title = { Text("OPM Brain (Beta)") },
                        onClick = { navController.navigate("settings/echo_brain") }
                    )
                )
            }
            if (appearanceText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == appearanceText),
                        icon = painterResource(R.drawable.palette),
                        title = { Text(appearanceText) },
                        onClick = { navController.navigate("settings/appearance") }
                    )
                )
            }
            if (playerText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == playerText),
                        icon = painterResource(R.drawable.play),
                        title = { Text(playerText) },
                        onClick = { navController.navigate("settings/player") }
                    )
                )
            }
            if (listenTogetherText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == listenTogetherText),
                        icon = painterResource(R.drawable.group),
                        title = { Text(listenTogetherText) },
                        onClick = { navController.navigate(Screens.ListenTogether.route) }
                    )
                )
            }
            if (contentText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == contentText),
                        icon = painterResource(R.drawable.language),
                        title = { Text(contentText) },
                        onClick = { navController.navigate("settings/content") }
                    )
                )
            }
            if (aiLyricsText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == aiLyricsText),
                        icon = painterResource(R.drawable.translate),
                        title = { Text(aiLyricsText) },
                        onClick = { navController.navigate("settings/ai") }
                    )
                )
            }
            if (privacyText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == privacyText),
                        icon = painterResource(R.drawable.security),
                        title = { Text(privacyText) },
                        onClick = { navController.navigate("settings/privacy") }
                    )
                )
            }
            if (storageText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == storageText),
                        icon = painterResource(R.drawable.storage),
                        title = { Text(storageText) },
                        onClick = { navController.navigate("settings/storage") }
                    )
                )
            }
            if (backupText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == backupText),
                        icon = painterResource(R.drawable.restore),
                        title = { Text(backupText) },
                        onClick = { navController.navigate("settings/backup_restore") }
                    )
                )
            }
            if (systemUpdateText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == systemUpdateText),
                        icon = painterResource(if (isUpdateAvailable) R.drawable.ic_notification else R.drawable.update),
                        title = { Text(systemUpdateText) },
                        description = if (isUpdateAvailable) {
                            {
                                Text(
                                    text = stringResource(R.string.update_available),
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null,
                        onClick = { navController.navigate("settings/update") }
                    )
                )
            }
            if (aboutText.lowercase().contains(searchLower)) {
                add(
                    Material3SettingsItem(
    isHighlighted = (highlightKey == aboutText),
                        icon = painterResource(R.drawable.ic_notification),
                        tintIcon = false,
                        title = { Text(aboutText) },
                        description = { Text("Version, credits, and premium OPM identity") },
                        onClick = { navController.navigate("settings/about") }
                    )
                )
            }

        }

        val finalItemsList = if (searchQuery.isNotEmpty()) {
            val subSettings = getAllSearchableSettings()

            val matchedSubSettings = subSettings
                .filter { it.first.lowercase().contains(searchLower) }
                .groupBy { it.second }
                .map { (parentTitle, settingsInPage) ->
                    val route = settingsInPage.first().third
                    val title = settingsInPage.first().first
                    Material3SettingsItem(
                        icon = painterResource(R.drawable.search),
                        title = { Text(parentTitle) },
                        description = { Text("Contains ${settingsInPage.size} matching setting(s)") },
                        onClick = { 
                            val encodedTitle = java.net.URLEncoder.encode(title, "UTF-8")
                            val finalRoute = if (route.contains("?")) "$route&highlightKey=$encodedTitle" else "$route?highlightKey=$encodedTitle"
                            navController.navigate(finalRoute)
                        }
                    )
                }
            
            itemsList + matchedSubSettings
        } else {
            itemsList
        }

        if (finalItemsList.isEmpty() && searchQuery.isNotEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "No settings found for \"$searchQuery\"",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White.copy(alpha = 0.64f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        } else {
            Material3SettingsGroup(scrollState = scrollState, items = finalItemsList)
        }
        
            Spacer(modifier = Modifier.height(50.dp))
        Spacer(
            Modifier.windowInsetsPadding(
                LocalPlayerAwareWindowInsets.current.only(WindowInsetsSides.Bottom)
            )
        )
    }

    TopAppBar(
        title = {
            androidx.compose.animation.AnimatedVisibility(
                visible = scrollState.value > 100,
                enter = androidx.compose.animation.fadeIn(),
                exit = androidx.compose.animation.fadeOut()
            ) {
                Text(
                    text = stringResource(R.string.settings),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = navController::navigateUp,
                onLongClick = navController::backToMain
            ) {
                Icon(
                    painterResource(R.drawable.arrow_back),
                    contentDescription = null
                )
            }
        }
    )
}
