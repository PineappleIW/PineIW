<%@ include file="../fragments/header.jspf" %>

    <div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input class="form-control" placeholder="Email address" required="" autofocus="" type="text" name="login">
        <input class="form-control" placeholder="Password" required="" type="password" name="pass">
        <label class="checkbox">
          <input value="remember-me" type="checkbox"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="logear">Sign in</button>
      </form>

    </div> 
    
 
<c:if test="${loginError!=null}"> 
${loginError}
</c:if>


<%@ include file="../fragments/footer.jspf" %>