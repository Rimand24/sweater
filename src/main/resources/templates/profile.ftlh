<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${username}</h5>
    ${message!}
    <form method="post">

        <div class="form-group-row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="password"/>
            </div>
        </div>

            <div class="form-group-row">
                <label class="col-sm-2 col-form-label">Email:</label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control" placeholder="email" value="${email!''}"/>
                </div>
            </div>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>

</@c.page>


<#--<div class="form-group row">-->
<#--    <label class="col-sm-2 col-form-label">Password:</label>-->
<#--    <div class="col-sm-6">-->
<#--        <input type="password" name="password"-->
<#--               class="form-control ${(passwordError??)?string('is-invalid', '')}"-->
<#--               placeholder="Password" />-->
<#--        <#if passwordError??>-->
<#--            <div class="invalid-feedback">-->
<#--                ${passwordError}-->
<#--            </div>-->
<#--        </#if>-->
<#--    </div>-->
<#--</div>-->
<#--<div class="form-group row">-->
<#--    <label class="col-sm-2 col-form-label">Confirm password:</label>-->
<#--    <div class="col-sm-6">-->
<#--        <input type="password" name="passwordConfirm"-->
<#--               class="form-control ${(passwordConfirmError??)?string('is-invalid', '')}"-->
<#--               placeholder="Retype password" />-->
<#--        <#if passwordConfirmError??>-->
<#--            <div class="invalid-feedback">-->
<#--                ${passwordConfirmError}-->
<#--            </div>-->
<#--    </div>-->
<#--</div>-->