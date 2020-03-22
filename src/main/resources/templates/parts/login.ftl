<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user??>${user.username}</#if>"
                       class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       placeholder="User name" />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password" />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Password:</label>
                <div class="col-sm-6">
                    <input type="password" name="passwordConfirm"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                           placeholder="Retype password" />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string('is-invalid', '')}"
                           placeholder="some@some.com" />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Add new user</a></#if>
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>

<#--    <form action="${path}" method="post">-->
<#--        <div class="form-group-row">-->
<#--            <label class="col-sm-2 col-form-label">User Name:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="text" name="username" class="form-control" placeholder="User name"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        <div class="form-group-row">-->
<#--            <label class="col-sm-2 col-form-label">Password:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="password" name="password" class="form-control" placeholder="password"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        <#if isRegisterForm>-->
<#--        <div class="form-group-row">-->
<#--            <label class="col-sm-2 col-form-label">Email:</label>-->
<#--            <div class="col-sm-6">-->
<#--                <input type="email" name="email" class="form-control" placeholder="email"/>-->
<#--            </div>-->
<#--        </div>-->
<#--        </#if>-->
<#--        <#if !isRegisterForm>-->
<#--        <div class="form-group form-check">-->
<#--            <input type="checkbox" class="form-check-input" id="remember">-->
<#--            <label class="form-check-label" for="remember">Remember me</label>-->
<#--        </div>-->
<#--            <div><a href="/registration">Register</a><br></div>-->
<#--        </#if>-->
<#--        <br>-->
<#--        <input type="hidden" name="_csrf" value="${_csrf.token}" />-->
<#--        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>-->
<#--    </form>-->
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Sign Out</button>
    </form>
</#macro>