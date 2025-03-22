package edu.bu.metcs.projectportallab.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.bu.metcs.projectportallab.Project
import edu.bu.metcs.projectportallab.ui.theme.ProjectPortalLabTheme


@Composable
fun ProjEdit(){
    var projTitle by remember {mutableStateOf(Project.project.title)}
    var projDesp by remember{ mutableStateOf(Project.project.description)}
    var canEdit by remember{mutableStateOf(false)}
    var projAuth by remember { mutableStateOf(Project.project.authors)}
    var projLink by remember { mutableStateOf(Project.project.link)}
    var projFav by remember { mutableStateOf(Project.project.isFavorite) }
    var projKeyword by remember { mutableStateOf(Project.project.keywords) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
    ){
        Button(
            onClick = {
                if (canEdit) {
                    Project.project.title = projTitle
                    Project.project.description = projDesp
                }
                canEdit = !canEdit
            },
            modifier = Modifier.align(Alignment.End)
        ){
            Text(if(canEdit)  "Submit" else "Edit")
        }

        BasicTextField(
            value = projTitle,
            onValueChange = {projTitle = it},
            textStyle = TextStyle(color = Color.Blue,
                fontSize = 36.sp),
            modifier = Modifier.fillMaxWidth(),
            enabled = canEdit,
        )


        Divider()
        Spacer(Modifier.fillMaxWidth().size(32.dp))

        LabeledTextField(
            label = "Author",
            value = projAuth,
            onValueChange = {projAuth = it},
            textStyle = TextStyle(
                fontSize = 18.sp),
        )
        Spacer(Modifier.fillMaxWidth().size(32.dp))

        Text(
            text = "Project Description",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.fillMaxWidth().size(16.dp))

        BasicTextField (
            value = projDesp,
            onValueChange = {projDesp = it},
            textStyle = TextStyle(
                fontSize = 18.sp),
            enabled = canEdit,
            modifier = Modifier.fillMaxWidth()

        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        LabeledTextField(
            label = "Link",
            value = projLink,
            onValueChange = {projLink = it},
            textStyle = TextStyle(
                fontSize = 18.sp)
        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = projFav,
                onCheckedChange = { projFav = it }
            )
            Text("Mark as Favorite")
        }

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        KeywordList(
            keywords = projKeyword,
            onKeywordsChange = { updatedList ->
                projKeyword = updatedList
            }
        )
    }

}

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle()
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 5.dp)
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.width(60.dp),
            fontWeight = FontWeight.SemiBold
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            singleLine = true
        )
    }
}

@Composable
fun KeywordList(
    keywords: List<String>,
    onKeywordsChange: (List<String>) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(keywords) { index, keyword ->
            TextField(
                value = keyword,
                onValueChange = { newValue ->
                    // Create a new list with the updated value
                    val updatedKeywords = keywords.toMutableList()
                    updatedKeywords[index] = newValue
                    onKeywordsChange(updatedKeywords)
                },
                label = { Text("Keyword ${index + 1}") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(name = "Project")
@Composable
private fun ProjEditPreview() {
    ProjectPortalLabTheme {
        ProjEdit()
    }
}