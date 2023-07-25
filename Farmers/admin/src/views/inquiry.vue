<template>  
   <v-data-table :headers="headers" :items="desserts"></v-data-table>
</template>


<script>
export default {
  data() {
    return {
      headers: [
        { text: 'Dessert', value: 'name' },
        { text: 'Calories', value: 'calories' },
        { text: 'Fat (g)', value: 'fat' },
        { text: 'Carbs (g)', value: 'carbs' },
        { text: 'Protein (g)', value: 'protein' },
      ],
      desserts: [
        { name: 'Frozen Yogurt', calories: 159, fat: 6.0, carbs: 24, protein: 4.0 },
        { name: 'Ice cream sandwich', calories: 237, fat: 9.0, carbs: 37, protein: 4.3 },
        // Add more data here...
      ],
    };
  },
};
</script>