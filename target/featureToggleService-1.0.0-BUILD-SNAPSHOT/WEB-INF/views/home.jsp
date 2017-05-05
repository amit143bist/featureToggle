<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="ff4j" uri="http://www.ff4j.org/taglibs/ff4j"%>
<%@ page session="false"%>
<html>
	<head>
		<title>Home - Feature Toggle Service</title>
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
			
			
		<script>
			$(document).ready(function(){
				
				
					$("#batchStatusBtn").click(function(){
										
							var batchStatus = $("#batchStatusOption").val();
							
							$.ajax({
				                url: 'clientCanView' + "?clientName=" + batchStatus,
				                type: 'GET',
				                dataType: 'json',
				                success: function (result) {
				                	
				                   console.log('result- ' + result);
				             	   $('#clientStatus').html(result.toString());
		
				                }
				            });
							
						});
						
					});
			
			
		</script>
	</head>
	<body>
		<h1>Hello world!</h1>
	
		<P>The time on the server is ${serverTime}.</P>
	
	
		<ff4j:enable featureid="TimeFeature">
	  		TimeFeature is up
		</ff4j:enable>
	
		<ff4j:disable featureid="TimeFeature">
	  		TimeFeature is down
		</ff4j:disable>
		
		
		<div>Select Company Name</div>
		<div>
			<select id="batchStatusOption" name="batchStatusOption">
				<option value="FoxRoach">FoxRoach</option>
				<option value="PacificUnion">PacificUnion</option>
				<option value="BostonLogic">BostonLogic</option>
				<option value="StewartTitle">StewartTitle</option>
			</select>
		</div>
		
		<input type="button" value="Search" id="batchStatusBtn" name="batchStatusBtn"/>
		
		<div id="clientStatus"></div>
	
	</body>
</html>