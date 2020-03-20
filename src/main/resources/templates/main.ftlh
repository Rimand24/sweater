<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<@l.logout/>
        <form method="post">
            <input type="text" name="text" placeholder="text here" />
            <input type="text" name="tag" placeholder="tag here">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    <div>Message List</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
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
    </div>
<#else>
    No messages
</#list >








</@c.page>



