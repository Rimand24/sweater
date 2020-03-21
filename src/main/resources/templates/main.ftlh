<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group com-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter!}">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseList" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
<div class="collapse" id="collapseList">
    <div class="form-group">


    </div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="text here" />
            <input type="text" name="tag" placeholder="tag here">
            <input type="file" name="file" placeholder="choose file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>

<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}">
            </#if>
        </div>
    </div>
<#else>
    No messages
</#list >








</@c.page>



