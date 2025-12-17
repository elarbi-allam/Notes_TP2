package com.elarbiallam.notes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    private GridView gridView;
    private List<String> imagePaths; // Liste des chemins de fichiers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        gridView = findViewById(R.id.gridViewGallery);
        imagePaths = new ArrayList<>();

        // 1. Récupérer les images du dossier privé
        loadImages();

        // 2. Afficher dans la grille via un Adapter
        if (imagePaths.isEmpty()) {
            Toast.makeText(this, "Aucune photo trouvée", Toast.LENGTH_SHORT).show();
        } else {
            gridView.setAdapter(new ImageAdapter(this, imagePaths));
        }
    }

    private void loadImages() {
        // On cible le même dossier que dans CameraActivity
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (storageDir != null && storageDir.exists()) {
            File[] files = storageDir.listFiles();
            if (files != null) {
                // On parcourt tous les fichiers pour trouver les images JPG
                for (File file : files) {
                    if (file.getName().endsWith(".jpg")) {
                        imagePaths.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }

    private class ImageAdapter extends BaseAdapter {
        private Context context;
        private List<String> paths;

        public ImageAdapter(Context context, List<String> paths) {
            this.context = context;
            this.paths = paths;
        }

        @Override
        public int getCount() { return paths.size(); }

        @Override
        public Object getItem(int position) { return paths.get(position); }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // Si la vue n'existe pas, on la crée
                imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300)); // Taille carré
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(4, 4, 4, 4);
            } else {
                imageView = (ImageView) convertView;
            }

            // Chargement optimisé de l'image pour éviter de saturer la mémoire
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // On réduit la qualité par 4 pour l'affichage rapide (Thumbnails)
            Bitmap bitmap = BitmapFactory.decodeFile(paths.get(position), options);

            imageView.setImageBitmap(bitmap);

            return imageView;
        }
    }
}