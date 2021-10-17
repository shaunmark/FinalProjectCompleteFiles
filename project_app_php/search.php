<?php

include 'config.php';
$con = mysqli_connect(HOST,USER,PASS,DB);


if($_SERVER['REQUEST_METHOD']=='POST'){
   $json = file_get_contents('php://input');
   $obj = json_decode($json);
   $platenumber = $obj->{'plateNumber'};

 //$username = '';
 //$password = '';

   $details_fetch_sql = "SELECT Plate_Number, IN_Date, IN_Time, OUT_Date, OUT_Time  from vehicle_data  WHERE Plate_Number ='".$platenumber."' ";

   $resulti = mysqli_query($con,$details_fetch_sql);

   $json_array = array();

   while($row = mysqli_fetch_assoc($resulti))
   {
     $json_array[] = $row;
   }

  echo json_encode($json_array);

 }
 else
 {
   $name["response"]="Failed";
   echo json_encode($name);
 }
 mysqli_close($con);
?>
