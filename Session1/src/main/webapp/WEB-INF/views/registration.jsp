<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
      <!--Import Google Icon Font-->
      <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
      <!--Import materialize.css-->
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/materialize.min.css"  media="screen,projection"/>

      <!--Let browser know website is optimized for mobile-->
      <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	  <title>Party reservation</title>
	
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
</head>

<body>
	<!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/materialize.min.js"></script>

	<div class="row">
		<form:form class="col s12" id="partyRegistrationForm" name="partyRegistrationForm" action="confirmRegistration" method="post" modelAttribute="partyRegistrationForm">
			<div class="row">
				<div class="input-field col s6">
					<i class="material-icons prefix">account_circle</i>
					<form:input path="firstName" id="first_name" type="text"
						class="validate" aria-required="true" required="true"/>
					<label for="first_name"><spring:message code="registration.firstName"/></label>
					<div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
				</div>
				<div class="input-field col s6">
					<i class="material-icons prefix">account_circle</i>
					<form:input path="lastName" id="last_name" type="text" class="validate" aria-required="true" required="true"/> 
					<label for="last_name"><spring:message code="registration.lastName"/></label>
					<div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s6">
					<i class="material-icons prefix">phone</i>
					<form:input path="phoneNumber" id="phone_number" type="tel" class="validate" aria-required="true" required="true"/> 
					<label for="phone_number"><spring:message code="registration.phoneNumber"/></label>
					<span id="spnPhoneStatus" class="waves-effect"></span>
					<div class="has-error">
                        <form:errors path="phoneNumber" class="help-inline"/>
                    </div>
				</div>
			</div>
			
			<div class="input-field col s12">
                <button class="btn waves-effect waves-light" type="submit" name="action"><spring:message code="registration.submit"/></button>
            </div>
		</form:form>
	</div>
</body>
</html>