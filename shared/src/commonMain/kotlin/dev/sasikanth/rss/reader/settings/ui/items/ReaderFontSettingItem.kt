/*
 * Copyright 2026 Sasikanth Miriyampalli
 *
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package dev.sasikanth.rss.reader.settings.ui.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.sasikanth.rss.reader.components.DropdownMenu
import dev.sasikanth.rss.reader.components.DropdownMenuItem
import dev.sasikanth.rss.reader.data.repository.ReaderFont
import dev.sasikanth.rss.reader.data.repository.isPremium
import dev.sasikanth.rss.reader.resources.icons.StarShine
import dev.sasikanth.rss.reader.resources.icons.TwineIcons
import dev.sasikanth.rss.reader.ui.AppTheme
import org.jetbrains.compose.resources.stringResource
import twine.shared.generated.resources.Res
import twine.shared.generated.resources.settingsFont

@Composable
internal fun ReaderFontSettingItem(
  readerFont: ReaderFont,
  isSubscribed: Boolean,
  onReaderFontChanged: (ReaderFont) -> Unit,
  modifier: Modifier = Modifier,
) {
  var showDropdown by remember { mutableStateOf(false) }

  Box {
    Row(
      modifier =
        Modifier.clickable(onClick = { showDropdown = true })
          .padding(horizontal = 24.dp, vertical = 16.dp)
          .fillMaxWidth()
          .then(modifier),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
      Column(Modifier.weight(1f)) {
        Text(
          text = stringResource(Res.string.settingsFont),
          style = MaterialTheme.typography.titleSmall,
          color = AppTheme.colorScheme.onSurface,
        )

        Text(
          text = readerFont.value,
          style = MaterialTheme.typography.bodyMedium,
          color = AppTheme.colorScheme.onSurfaceVariant,
        )
      }

      Icon(
        modifier = Modifier.size(20.dp),
        imageVector = TwineIcons.ArrowDown,
        contentDescription = null,
        tint = AppTheme.colorScheme.onSurfaceVariant,
      )
    }

    DropdownMenu(
      expanded = showDropdown,
      onDismissRequest = { showDropdown = false },
    ) {
      ReaderFont.entries.forEach { font ->
        val isPremium = font.isPremium && !isSubscribed

        DropdownMenuItem(
          text = font.value,
          selected = font == readerFont,
          leadingIcon = if (isPremium) TwineIcons.StarShine else null,
          onClick = {
            onReaderFontChanged(font)
            showDropdown = false
          },
        )
      }
    }
  }
}
