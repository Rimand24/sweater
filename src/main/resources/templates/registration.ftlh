<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<@l.login "/registration" true/>
    <div class="mb-1"></div>
${message!}
</@c.page>