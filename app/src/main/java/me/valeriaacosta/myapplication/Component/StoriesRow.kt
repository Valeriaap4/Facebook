package me.valeriaacosta.myapplication.Component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Story(
    val id: Int,
    val userName: String
)

private val StoryBlue = Color(0xFF4A90E2)
private val StoryAccentBlue = Color(0xFF1877F2)
private val CreateStoryGray = Color(0xFFF0F2F5)

private val sampleStories = listOf(
    Story(1, "Laura"),
    Story(2, "Carlos"),
    Story(3, "Ana"),
    Story(4, "Pedro"),
    Story(5, "Sofía"),
)

@Composable
fun StoriesRow(
    modifier: Modifier = Modifier,
    stories: List<Story> = sampleStories,
    onCreateStoryClick: () -> Unit = {},
    onStoryClick: (Story) -> Unit = {}
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Primera tarjeta: "Crear historia"
        item {
            CreateStoryCard(onClick = onCreateStoryClick)
        }
        // Historias de los usuarios
        items(stories, key = { it.id }) { story ->
            StoryCard(story = story, onClick = { onStoryClick(story) })
        }
    }
}

@Composable
private fun CreateStoryCard(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.size(width = 110.dp, height = 190.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = CreateStoryGray)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(StoryAccentBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Crear historia",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Crear historia",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1C1E21)
            )
        }
    }
}

@Composable
private fun StoryCard(story: Story, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.size(width = 110.dp, height = 190.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = StoryBlue)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Degradado oscuro abajo para que el nombre se lea bien
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black.copy(alpha = 0.35f))
                        )
                    )
            )

            // Foto de perfil arriba a la izquierda
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, StoryAccentBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de ${story.userName}",
                    tint = Color.LightGray,
                    modifier = Modifier.size(28.dp)
                )
            }

            // Nombre abajo a la izquierda
            Text(
                text = story.userName,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoriesRowPreview() {
    StoriesRow()
}