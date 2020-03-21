<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group-row">
            <label class="col-sm-2 col-form-label">User Name:</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group-row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>
        <#if !isRegisterForm>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="remember">
            <label class="form-check-label" for="remember">Remember me</label>
        </div>
            <div><a href="/registration">Register</a><br></div>
        </#if>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
<#--        <input type="submit" value="Sign Out"/>-->
    </form>
</#macro>

<#macro loginless path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group col-sm-6">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" placeholder="User name">
        </div>
        <div class="form-group col-sm-6">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="password">
        </div>
        <div class="form-group form-check">
            <input type="checkbox" class="form-check-input" id="remember">
            <label class="form-check-label" for="remember">Remember me</label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Register</a><br></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        <#--    <button type="submit" class="btn btn-primary">Submit</button>-->
    </form>
</#macro>