<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$OfficerName= $_POST["OfficerName"];
		$directorate= $_POST["directorate"];
		$designation= $_POST["designation"];
		$mobile= $_POST["mobile"];
		$Email= $_POST["Email"];
		$PurposeOfVisit= $_POST["PurposeOfVisit"];
		$PlaceForVisit= $_POST["PlaceForVisit"];
		$Time= $_POST["Time"];
		$Address= $_POST["Address"];
		$Date= $_POST["Date"];
		if($OfficerName == '' || $directorate == '' || $designation == '' || $mobile == ''|| $Email == ''|| $PurposeOfVisit == ''|| $PlaceForVisit == ''|| $Time == ''|| $Address == ''|| $Date == ''){
			echo 'please fill all values';
		}else{
			require_once('finalConn.php');
			$sql = "SELECT * FROM vehicalform WHERE  Email='$Email'";
			
			$check = mysqli_fetch_array(mysqli_query($iconn,$sql));
			
			if(isset($check)){
				echo 'Email already exist';
			}else{				
				$sql = "INSERT INTO vehicalform (OfficerName,directorate,designation,mobile,
Email,PurposeOfVisit,PlaceForVisit,Time,Address,Date) values('$OfficerName','$directorate','$designation',
'$mobile','$Email','$PurposeOfVisit','$PlaceForVisit','$Time','$Address','$Date')";
				if(mysqli_query($iconn,$sql)){
					echo 'successfully submitted';
				}else{
					echo 'oops! Please try again!';
				}
			}
			mysqli_close($iconn);
		}
}else{
echo 'error';
}