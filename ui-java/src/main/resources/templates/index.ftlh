<#-- @ftlvariable name="model" type="todoapp.uijava.TodoUiController.IndexModel" -->
<#import "entry.inc.ftlh" as entry>

<#include "header.inc.ftlh">

<a href="add" class="button success">Add new</a>
<a href="assignee-report" class="button">Report</a>

<#if model.high()?has_content>
    <h2>High priority</h2>
    <#list model.high() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>
<#if model.medium()?has_content>
    <h2>Medium priority</h2>
    <#list model.medium() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>
<#if model.low()?has_content>
    <h2>Low priority</h2>
    <#list model.low() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>
<#if model.others()?has_content>
    <h2>No priority set</h2>
    <#list model.others() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>
<#if model.inProgress()?has_content>
    <h2>In Progress</h2>
    <#list model.inProgress() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>
<#if model.completed()?has_content>
    <h2>Completed</h2>
    <#list model.completed() as item>
        <li><@entry.render item/></li>
    </#list>
</#if>

<#include "footer.inc.ftlh">
