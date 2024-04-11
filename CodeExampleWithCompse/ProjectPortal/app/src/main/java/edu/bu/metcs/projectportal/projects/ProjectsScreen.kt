@file:OptIn(ExperimentalMaterial3Api::class)

package edu.bu.metcs.projectportal.projects

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import edu.bu.metcs.projectportal.R
import edu.bu.metcs.projectportal.data.Project
import edu.bu.metcs.projectportal.ui.theme.ProjectPortalTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjsScreen(
    onAddProj: () -> Unit,
    onSelectProj: (Project) -> Unit,
    onAboutUS: ()->Unit,
    modifier: Modifier = Modifier,
    viewModel: ProjectsViewModel = hiltViewModel(),
) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text (stringResource(id = R.string.app_name))},
                navigationIcon = {},
                actions = {
                    IconButton(onClick = { onAboutUS() }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "About US"
                        )
                    }
                }
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.common_padding)),
        floatingActionButton = {
            FloatingActionButton(onClick = onAddProj) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.add_project))
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            // collect value emitted by uiState from the viewModel
            val uiState by viewModel.uiState.collectAsState()
          //  val projs by viewModel.kotlinProjs.collectAsState()
            val projs by viewModel.searchResult.collectAsState()
            var keywords by remember { mutableStateOf("") }
            var value by remember { mutableStateOf("") }

            Row {

                TextField(
                    value = value,
                    onValueChange = {
                        value = it
                        // search whenever the input changes
                        viewModel.updateSearchResult(value)
                                    },
                   label = {Text("search")},
                    modifier = Modifier
                    .padding(dimensionResource(R.dimen.common_padding))
                )
                 // Click search icon to search
//                IconButton(onClick = {
//                    keywords = value
//                    viewModel.updateSearchResult(keywords)
//                }) {
//                    Icon(
//                        imageVector = Icons.Filled.Search,
//                        contentDescription = "Search"
//                    )
//                }
            }

            if (value == "") {
                ProjList(
                    uiState.allProjects, onSelectProj,
                    onDeleteProj = viewModel::deleteProj,
                    modifier = Modifier
                      //  .padding(paddingValues)
                )
            } else
                ProjList(
                    projs, onSelectProj,
                    onDeleteProj = viewModel::deleteProj,
                    modifier = Modifier
                     //   .padding(paddingValues)
                )
        }
    }
}


@Composable
fun ProjList (
    projs: List<Project>,
    onSelectProj: (Project) -> Unit,
    onDeleteProj: (String) -> Unit,
    modifier: Modifier
)  {
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen.common_padding))
    ){

            Text(
                text = stringResource(id = R.string.app_header),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.common_padding)),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

        HorizontalDivider(modifier= Modifier)
        LazyColumn{
            itemsIndexed(
                items = projs,
                key = { _, proj -> proj.id }
            ) { index, proj ->
                ProjCard(
                    index,
                    proj = proj,
                    onClick = onSelectProj,
                    onDelete = onDeleteProj)
            }
        }
    }
}

@Composable
fun ProjCard(
    index: Int,
    proj: Project,
    onClick: (Project) -> Unit,
    onDelete: (String)-> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.common_padding))
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(proj) }
        ) {
            Text(text = (index+1).toString(),
                modifier = Modifier.padding(dimensionResource(id = R.dimen.common_padding)))
            Text(text = proj.title,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.common_padding)))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {onDelete(proj.id)}
            ) {
                Icon(Icons.Filled.Delete, stringResource(id = R.string.del_project))
            }
        }
    }
}



@Preview(name = "List all Projects")
@Composable
private fun ProjListPreview() {
    ProjectPortalTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            ProjList(
                projs = listOf(
                    Project ("1", "weather forcast", "this app is ..."),
                    Project ("2", "Project Portal", "this app is ..."),

                    ),
                onSelectProj = {},
                onDeleteProj = {},
                modifier = Modifier.fillMaxWidth())
        }
    }
}