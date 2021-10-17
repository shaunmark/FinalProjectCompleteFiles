<?php

include 'config.php';
$con = mysqli_connect(HOST,USER,PASS,DB);

if($_SERVER['REQUEST_METHOD']=='GET'){
  $json = file_get_contents('php://input');
  $obj = json_decode($json);
  /*$LV_id = 1;
  $CO_id = 1;
  $CU_id = 1;
  $LS_id = 1;*/
  $details_fetch_sql = "SELECT Plate_Number, Registered, IN_Date,IN_Time, OUT_Date, OUT_Time  from vehicle_data";

  $resulti = mysqli_query($con,$details_fetch_sql);
/*
  $details_array = array(); //array

  $details_output =  array();

  if (mysqli_num_rows($details_fetch ) >0)
  {
    while($details_row = mysqli_fetch_array($details_fetch ))
    {
      $name["Plate_Number"] = $details_row["Plate_Number"];
      $name["Registered"] = $details_row["Registered"];
      $name["IN_Date"] = $details_row['IN_Date'];
      $name["IN_Time"] = $details_row['IN_Time'];
      $details_array[] = $name;
    }


    $details_output["Details"] = $details_array;
    echo json_encode($details_output);
  }
  */

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
