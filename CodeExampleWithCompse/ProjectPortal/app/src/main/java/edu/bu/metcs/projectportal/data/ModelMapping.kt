package edu.bu.metcs.projectportal.data

import edu.bu.metcs.projectportal.data.db.ProjectInLocalDB

fun Project.toLocal() = ProjectInLocalDB (
    id = id,
    title = title,
    description = description,
)

fun List<Project>.toLocal() = map(Project::toLocal)

fun ProjectInLocalDB.toModel() = Project (
    id = id,
    title = title,
    description = description,
)

fun List<ProjectInLocalDB>.toModel() = map (ProjectInLocalDB::toModel)