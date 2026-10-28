package me.valeriaacosta.myapplication.ui.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.valeriaacosta.myapplication.Component.PostCard
import me.valeriaacosta.myapplication.Component.StoriesRow

@Preview
@Composable
fun FeedScreen(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        item { StoriesRow() }
        item { HorizontalDivider() }
        items(3) { PostCard() }
    }
}
//en este commit cree el feedScreen a pesar de que no se incluya en la tarea,
//para probar el funcionamiento de la LazyRow y la navegacion :)