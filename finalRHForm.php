<?php
if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$BookedFor= $_POST["BookedFor"];
		$BookedOn= $_POST["BookedOn"];
		$Official= $_POST["Official"];
		$OfficialDesignation= $_POST["OfficialDesignation"];
		$officerMobile= $_POST["officerMobile"];
		$officeMobile= $_POST["officeMobile"];
		$Orginization= $_POST["Orginization"];
		$Email= $_POST["Email"];
		$personNo= $_POST["personNo"];
		if($BookedFor == '' || $BookedOn == '' || $Official == '' || $OfficialDesignation == ''|| $officerMobile == ''|| $officeMobile == ''|| $officeMobile == ''|| $Orginization == ''|| $Email == ''|| $personNo == ''){
			echo 'please fill all values';
		}else{
			require_once('finalConn.php');
			$sql = "SELECT * FROM resthouseform WHERE  Email='$Email'";
			
			$check = mysqli_fetch_array(mysqli_query($iconn,$sql));
			
			if(isset($check)){
				echo 'Email already exist';
			}else{				
				$sql = "INSERT INTO resthouseform (BookedFor,BookedOn,Official,OfficialDesignation,officerMobile,officeMobile,Orginization,Email,personNo) 
				values('$BookedFor','$BookedOn','$Official',
'$OfficialDesignation','$officerMobile','$officeMobile','$Orginization','$Email','$personNo')";
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