<template>
    <div style="margin: -10px -15px 0 -15px;">
        <v-card-text>
            <div class="label-title"></div>
            <div v-if="editMode">
                <v-select
                    v-model="value"
                    :label="label"
                    :items="selections"
                    solo
                ></v-select>
            </div>
            <div v-else>
                Type : {{ value }}
            </div>
        </v-card-text>
    </div>
</template>

<script>
import BaseEntity from './base-ui/BaseEntity.vue';

export default {
    name: 'Type',
    mixins:[BaseEntity],
    components:{},
    props: {
        modelValue: Object,
        label: String
    },
    data: () => ({
        selections : ["FAQ","NOTICE",],
    }),
    async created() {
        this.value = this.modelValue
        if(!this.value) {
            this.value = [];
        }
    },
    watch: {
        value:{
            handler: function(){
                this.$emit("update:modelValue", this.value)
            }
        }
    },
    methods: {
    }
}
</script>