<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<@l.logout/>
    <span><a href="/user">userlist</a></span>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="text here" />
            <input type="text" name="tag" placeholder="tag here">
            <input type="file" name="file" placeholder="choose file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    <div>Message List</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Set Tag Filter</button>
    </form>
    <form method="get" action="/main">
        <input type="text" name="filter">
        <button type="submit">Set Author Filter</button>
    </form>
<#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}"
            </#if>
        </div>
    </div>
<#else>
    No messages
</#list >








</@c.page>



