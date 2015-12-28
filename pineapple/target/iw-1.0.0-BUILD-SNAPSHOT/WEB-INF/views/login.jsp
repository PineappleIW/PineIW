<%@ include file="../fragments/header.jspf" %>

    <div class="container">

      <form class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input class="form-control" placeholder="Email address" required="" autofocus="" type="text">
        <input class="form-control" placeholder="Password" required="" type="password">
        <label class="checkbox">
          <input value="remember-me" type="checkbox"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>

    </div> 
<%@ include file="../fragments/footer.jspf" %>