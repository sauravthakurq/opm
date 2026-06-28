

package sauravthakur.opm.models

import com.music.innertube.models.YTItem
import sauravthakur.opm.db.entities.LocalItem

data class SimilarRecommendation(
    val title: LocalItem,
    val items: List<YTItem>,
)
