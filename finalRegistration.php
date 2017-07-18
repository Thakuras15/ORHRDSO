<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		$officerName = $_POST['officerName'];
		$directorate = $_POST['directorate'];
		$designation = $_POST['designation'];
		$email = $_POST['email'];
		$password = $_POST['password'];
		$mobile = $_POST['mobile'];
		
		if($officerName == '' || $directorate == '' || $designation == '' || $email == ''|| $password == ''|| $mobile == ''){
			echo 'please fill all values';
		}else{
			require_once('finalConn.php');
			$sql = "SELECT * FROM registration WHERE officerName='$officerName' OR email='$email'";
			
			$check = mysqli_fetch_array(mysqli_query($iconn,$sql));
			
			if(isset($check)){
				echo 'username or email already exist';
			}else{				
				$sql = "INSERT INTO registration (officerName,directorate,designation,email,password,mobile) values('$officerName','$directorate','$designation',
'$email','$password','$mobile')";
				if(mysqli_query($iconn,$sql)){
					echo 'successfully registered';
				}else{
					echo 'oops! Please try again!';
				}
			}
			mysqli_close($iconn);
		}
}else{
echo 'error';
}