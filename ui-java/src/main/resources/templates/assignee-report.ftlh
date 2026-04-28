<#-- @ftlvariable name="model" type="todoapp.uijava.TodoUiController.AssigneeReportModel" -->
<#include "header.inc.ftlh">

<#list model.report().map() as assignee, entry>
    <#if assignee?has_content>
        <h2>Report for ${assignee}</h2>
    <#else>
        <h2>Unassigned todos</h2>
    </#if>
    <p>${entry.completed()?size} completed, ${entry.inProgress()?size} In progress, ${entry.waiting()?size} Waiting</p>
</#list>

<a href="/" class="button">Back</a>

<#include "footer.inc.ftlh">
