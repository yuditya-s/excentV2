<?php
require("koneksi.php");


if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_ec = $_POST["id_ec"];

	$query_ec = "SELECT name_ec, desc_ec, reg_question FROM table_ec WHERE id_ec = '$id_ec'";
	$result = mysqli_query($conn, $query_ec);

	if(mysqli_num_rows($result) > 0){
		
		$row = mysqli_fetch_assoc($result);

		$response['name_ec'] = $row['name_ec'];
		$response['desc_ec'] = $row['desc_ec'];
		$response['reg_question'] = $row['reg_question'];
	} else {
		$response['name_ec'] = "Tidak ditemukan";
		$response['desc_ec'] = "Tidak ditemukan";
		$response['reg_question'] = "Tidak ditemukan";	
	}
	
}

echo json_encode($response);
mysqli_close($conn);
?>