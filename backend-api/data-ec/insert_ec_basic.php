<?php

require_once("koneksi.php");

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	
	$id_user = $_POST["id_user"];
	$status = $_POST["status"];

	$name_ec = $_POST['name_ec'];
	$desc_ec = $_POST['desc_ec'];
	$reg_question = $_POST['reg_question'];

	$query_insert_ec = "INSERT INTO table_ec (name_ec, desc_ec, reg_question, id_user) VALUES ('$name_ec', '$desc_ec', '$reg_question', '$id_user')";

	if($status == "Guru"){
			$eksekusi = mysqli_query($conn, $query_insert_ec);
			$cek = mysqli_affected_rows($conn);
			if($cek > 0){
				$id_new_ec = mysqli_insert_id($conn);
				$response["success"] = true;
				$response["message"] = "Tambah data berhasil";
				$response["id_new_ec"] = $id_new_ec; //buat nullable
			}else{
				$response["success"] = false;
				$response["message"] = "Tambah data tidak berhasil";
			}
	} else {
		$response["success"] = false;
		$response["message"] = "Maaf Anda bukan user 'Guru'";
	}
	
echo json_encode($response);
mysqli_close($conn);
}
?>

