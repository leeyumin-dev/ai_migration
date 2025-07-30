<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '유저관리' }" />
        <VerticalNavLink
            :item="{
                title: 'User',
                to: '/users',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '게시판관리(공지사항,FAQ)' }" />
        <VerticalNavLink
            :item="{
                title: 'Post',
                to: '/posts',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'PostQuery',
                to: '/postQueries',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '에이전트관리' }" />
        <VerticalNavLink
            :item="{
                title: '에이전트관리',
                to: '/agents',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '관리자' }" />
        <VerticalNavLink
            :item="{
                title: 'Token',
                to: '/tokens',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '플랫폼 관리' }" />
        <VerticalNavLink
            :item="{
                title: 'Conversion',
                to: '/conversions',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'TestLog',
                to: '/testLogs',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'ConversionLog',
                to: '/conversionLogs',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'Security',
                to: '/securities',
            }"
        />
    </ul>
</template>
