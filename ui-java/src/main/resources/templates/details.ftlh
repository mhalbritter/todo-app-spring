<#-- @ftlvariable name="model" type="todoapp.uijava.TodoUiController.DetailsModel" -->
<#include "header.inc.ftlh">
<form method="post">
    <p>
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${model.entry().title()}"/>
    </p>
    <p>
        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="WAITING"<#if model.entry().status().name() == 'WAITING'> selected</#if>>Waiting</option>
            <option value="IN_PROGRESS"<#if model.entry().status().name() == 'IN_PROGRESS'> selected</#if>>In Progress</option>
            <option value="COMPLETED"<#if model.entry().status().name() == 'COMPLETED'> selected</#if>>Completed</option>
        </select>
    </p>
    <p>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${model.entry().description()!}"/>
    </p>
    <p>
        <label for="assignee">Assignee:</label>
        <input type="text" id="assignee" name="assignee" value="${model.entry().assignee()!}"/>
    </p>
    <p>
        <label for="priority">Priority:</label>
        <select id="priority" name="priority">
            <option value=""<#if !model.entry().priority()??> selected</#if>></option>
            <option value="LOW"<#if model.entry().priority()?? && model.entry().priority().name() == 'LOW'> selected</#if>>Low</option>
            <option value="MEDIUM"<#if model.entry().priority()?? && model.entry().priority().name() == 'MEDIUM'> selected</#if>>Medium</option>
            <option value="HIGH"<#if model.entry().priority()?? && model.entry().priority().name() == 'HIGH'> selected</#if>>High</option>
        </select>
    </p>

    <button class="success">Save</button>
    <a href="/" class="button">Back</a>
</form>
<#include "footer.inc.ftlh">
