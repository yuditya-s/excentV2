<?php

require_once 'koneksi.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$id_ec = $_POST['id_ec'];
	$media_content = $_POST['media_content'];
	$media_desc = $_POST['media_desc'];

	$query_insert_data_media = "INSERT INTO table_media(id_ec,media_type, media_desc) VALUES ('$id_ec','image', '$media_desc')";
	$eksekusi = mysqli_query($conn, $query_insert_data_media);
	$cek = mysqli_affected_rows($conn);

	if($cek > 0){
		$id_media_new = mysqli_insert_id($conn);
		$path = "img-ec/ec".$id_ec."_".$id_media_new.".jpeg";
		$finalpath = "data-ec/".$path;

		$query_update_data_media = "UPDATE table_media SET media_path = '$finalpath' WHERE id_media = '$id_media_new'";
		if(mysqli_query($conn, $query_update_data_media)){
			if(file_put_contents($path, base64_decode($media_content))) {
				$response["success"] = true;
				$response["message"] = "Gambar berhasil diupload!";
			} else {
				$response["success"] = false;
				$response["message"] = "Data berhasil dibuat tapi gambar berhasil diupload!";
			}
		} else {
			$response["success"] = false;
			$response["message"] = "Data tidak berhasil dibuat (2)";
	}
} else {
	$response["success"] = false;
	$response["message"] = "Data tidak berhasil dibuat (1)";
}
} else {
	$response["success"] = false;
	$response["message"] = "Tidak ada post data";
}
echo json_encode($response);
mysqli_close($conn);

?>


