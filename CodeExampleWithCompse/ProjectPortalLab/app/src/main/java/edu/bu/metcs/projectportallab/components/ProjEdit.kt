package edu.bu.metcs.projectportallab.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
    val authors by remember { mutableStateOf(Project.project.authors)}
    var projAuth by remember { mutableStateOf(authors.joinToString(", ")) }
    val links by remember { mutableStateOf(Project.project.link)}
    var projLink by remember { mutableStateOf(links.joinToString(", "))}
    var projFav by remember { mutableStateOf(Project.project.isFavorite) }
    val keywords by remember { mutableStateOf(Project.project.keywords) }
    var projKeyword by remember { mutableStateOf(keywords.joinToString(", ") ) }

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

        OutlinedTextField(
            value = projAuth,
            onValueChange = {projAuth = it},
            label = {Text("Project Author(s)")},
            enabled = canEdit,
            supportingText = {Text("separate authors by comma")}
        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        OutlinedTextField (
            value = projDesp,
            onValueChange = {projDesp = it},
            label = {Text("Project Description")},
            enabled = canEdit,
        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        OutlinedTextField(
            value = projLink,
            onValueChange = {projLink = it},
            label = {Text("Project Links")},
            enabled = canEdit,
            supportingText = {Text("separate links by comma")}
        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        OutlinedTextField(
            value = projKeyword,
            onValueChange = {projKeyword = it},
            label = {Text("Project Keywords")},
            supportingText = {Text("separate keywords by comma")},
            enabled = canEdit
        )

        Spacer(Modifier.fillMaxWidth().size(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = projFav,
                onCheckedChange = { projFav = it },
                enabled = canEdit
            )
            Text("Mark this project as favorite")
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