<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
Registration page
<@l.login "/registration" />
${message!}
</@c.page>