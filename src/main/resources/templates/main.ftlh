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

<#include "parts/messageEdit.ftl"/>
<#include "parts/messageList.ftl"/>

</@c.page>

