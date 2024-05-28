package com.apx.Devocao

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apx.Devocao.Adapter.CategorieLayoutAdapter
import com.apx.Devocao.MainActivity.Companion.categies
import com.apx.Devocao.Model.CategorieLayoutModel
import com.apx.Devocao.Model.CategorieModel
import com.google.firebase.firestore.FirebaseFirestore

class CategorieActivity : AppCompatActivity() {
    companion object {
        const val CATEGORY_KEY = "category_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)

        val category = intent.getStringExtra(CATEGORY_KEY)

        if (category != null) {
            consultarFrases(listOf(category))
        } else {
            // Lógica padrão para exibição de categorias
        }



        val list = findViewById<RecyclerView>(R.id.list)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        val general = findViewById<CardView>(R.id.general)
        general.setOnClickListener {
            categies = ""
            onBackPressed()
        }
        consultarFrases(listOf("Reflexao e Motivacao","Palavra do dia","Sabedoria","Gratidao","Fe e Confianca","Humildade e Perdao","Forca e Coragem","Esperanca e Renovacao"))


        val fav = findViewById<CardView>(R.id.fav)
        fav.setOnClickListener {
            startActivity(Intent(this, LikedActivity::class.java))
        }

        val own = findViewById<CardView>(R.id.own)
        own.setOnClickListener {
            startActivity(Intent(this, OwnActivity::class.java))
        }

        val collection = findViewById<CardView>(R.id.collection)
        collection.setOnClickListener {
            startActivity(Intent(this, CollActivity::class.java))
        }






        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        val _list =  ArrayList<CategorieLayoutModel>()
        val Improve_your_relationships  = ArrayList<CategorieModel>()
        val Thought_Provoking  = ArrayList<CategorieModel>()
        val Improve_your_mindset  = ArrayList<CategorieModel>()
        val Get_Inspired  = ArrayList<CategorieModel>()
        val Stay_mentally_strong  = ArrayList<CategorieModel>()
        val Follow_your_dreams  = ArrayList<CategorieModel>()
        val all  = ArrayList<CategorieModel>()

        all.add(CategorieModel(R.drawable.palavra_do_dia,"Palavra do dia", false))
        all.add(CategorieModel(R.drawable.reflex_o_e_motiva__o,"Reflexao e Motivacao", false))
        all.add(CategorieModel(R.drawable.fe_e_confian_a,"Fe e Confianca", false))
        all.add(CategorieModel(R.drawable.humildade_e_perdao,"Humildade e Perdao", false))
        all.add(CategorieModel(R.drawable.for_a_e_coragem,"Forca e Coragem", false))
        all.add(CategorieModel(R.drawable.gratid_o,"Gratidao", false))
        all.add(CategorieModel(R.drawable.sabedoria,"Sabedoria", false))
        all.add(CategorieModel(R.drawable.eperan_a_e_renova__o,"Esperanca e Renovacao", false))


        all.shuffle()
        val for_you = ArrayList<CategorieModel>()
        for (i in 0..6) {

            for_you.add(all[i])
        }
        _list.add(CategorieLayoutModel("Para Você", for_you))
        all.shuffle()

        val most_popular = ArrayList<CategorieModel>()
        for (i in 0..6) {
            most_popular.add(all[i])
        }
        _list.add(CategorieLayoutModel("Mais Populares", most_popular))

        Improve_your_relationships.add(CategorieModel(R.drawable.sabedoria,"Fe e Confianca", false))
        Improve_your_relationships.add(CategorieModel(R.drawable.for_a_e_coragem,"Forca e Coragem", false))
        Improve_your_relationships.add(CategorieModel(R.drawable.reflex_o_e_motiva__o,"Sabedoria", false))
        Improve_your_relationships.add(CategorieModel(R.drawable.humildade_e_perdao,"Humildade e Perdao", false))
        Improve_your_relationships.add(CategorieModel(R.drawable.cherish_loved_ones,"Palavra do dia", false))
        _list.add(CategorieLayoutModel("Aleatorios", Improve_your_relationships))

        list.adapter = CategorieLayoutAdapter(_list, this)


        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse)
    }

    fun consultarFrases(categorias: List<String>) {
        val db = FirebaseFirestore.getInstance()
        val dataCountPerCategory = mutableMapOf<String, Int>()

        for (categoria in categorias) {
            // Insira o log aqui
            Log.d(TAG, "Consultando a categoria: $categoria")

            db.collection("Frases")
                .whereEqualTo("cat", categoria)
                .get()
                .addOnSuccessListener { task ->
                    val data = ArrayList<MainModel>()
                    for (doc in task) {
                        data.add(
                            MainModel(
                                doc.id,
                                doc.getString("title").toString(),
                                doc.get("lag").toString(),
                                doc.get("auth").toString(),
                                doc.get("cat").toString()
                            )
                        )
                    }
                    dataCountPerCategory[categoria] = data.size
                    Log.d(TAG, "Consulta ao banco de dados concluída com sucesso. Frases da categoria 34 $categoria: ${data.size}")
                    Log.d(TAG, "Contagem de frases por categoria até agora: $dataCountPerCategory")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Erro ao consultar frases do banco de dados: ${e.message}", e)
                }
        }
    }





}