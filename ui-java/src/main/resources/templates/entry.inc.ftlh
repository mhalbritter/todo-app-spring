<#-- @ftlvariable name="entry" type="todoapp.domain.TodoEntry" -->
<#macro render entry>
    <a href="${entry.id().id()}">${entry.title()}</a> <#if entry.assignee()?has_content> (assigned to ${entry.assignee()})</#if>
</#macro>
