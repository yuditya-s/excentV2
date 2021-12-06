<?php

require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$id_ec = $_POST['id_ec'];

	$query = "SELECT media_path, media_desc FROM table_media WHERE media_type = 'image' AND id_ec = '$id_ec'";

	$result = mysqli_query($conn, $query);
	$response = array();
	$cek = mysqli_affected_rows($conn);

	if($cek > 0){
		while($row = mysqli_fetch_assoc($result)){
		array_push($response, 
			array(
				'media_path' => $row['media_path'],
				'media_desc' => $row['media_desc'])
			);
		}
	} else {
		array_push($response, 
			array(
				'media_path' => "data-ec/img-ec/no-gallery.jpeg",
				'media_desc' => "Penyelenggara ekstrakurikuler tidak menyediakan pustaka gambar"
				)
			);
	}
	
}
	echo json_encode($response);
	mysqli_close($conn);
?>