<template>
    <div>
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <String
            label="FilePath"
            v-model="value.filePath"
            :editMode="editMode"
        />
        <String
            label="InputeGovFrameVer"
            v-model="value.inputeGovFrameVer"
            :editMode="editMode"
        />
        <String
            label="OutputeGovFrameVer"
            v-model="value.outputeGovFrameVer"
            :editMode="editMode"
        />
        <Boolean
            label="IsTestCode"
            v-model="value.isTestCode"
            :editMode="editMode"
        />
        <String
            label="ConversionType"
            v-model="value.conversionType"
            :editMode="editMode"
        />
        <String
            label="Query"
            v-model="value.query"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Agent',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "agents",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
