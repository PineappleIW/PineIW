<%@ include file="../fragments/header.jspf" %>

    <div class="container">

      <form class="form-signin" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input class="form-control" placeholder="Login name" required="" autofocus="" type="text" name="login">
        <input class="form-control" placeholder="Password" required="" type="password" name="pass">
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="logear">Sign in</button>
      </form>

    </div> 
    
 
<c:if test="${e:forHtml(loginError!=null)}"> 
${e:forHtml(loginError)}
</c:if>


<%@ include file="../fragments/footer.jspf" %>