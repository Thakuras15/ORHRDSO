<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		$email = $_POST['email'];
		$password = $_POST['password'];
		
		require_once('finalConn.php');
		
		$sql = "select * from registration where email='$email' and password='$password'";
		
		$check = mysqli_fetch_array(mysqli_query($iconn,$sql));
		
		if(isset($check)){
			echo "success";
		}else{
			echo "Invalid Email or Password";
		}
		
	}else{
		echo "error try again";
	}