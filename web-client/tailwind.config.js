import {nextui} from '@nextui-org/theme'
import colors from 'tailwindcss/colors'

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './components/quizComponents/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './app/(quiz)/quiz/**/*.{js,ts,jsx,tsx,mdx}',
    './app/(restOfTheApp)/login/**/*.{js,ts,jsx,tsx,mdx}',
    './node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}',
    './src/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      fontFamily: {
        sans: ["var(--font-sans)"],
        mono: ["var(--font-geist-mono)"],
        inter: ["var(--font-)"]
      },
      /*backgroundImage: {
        'custom-bg': "url('@/accessories/Background.jpg')",
        'custom-bg-sm': "url('@/accessories/Background.jpg')",
        'custom-bg-lg': "url('@/accessories/Background.jpg')",
      },*/
    },
    colors: {
      'gradientPurple': '#9353D3',
      'gradientBlue': '#61a0d4',

    },
  },
  darkMode: "class",
  plugins: [nextui({
    themes: {
      dark: {
        colors: {
          //background: "#000000",
          'content1': '#006fee',
          'content1-opacity15': "rgba(0, 111, 238,0.15)",
          'content2': "#191919",
          "violet-600": colors.violet["500"],
          "blue-500": colors.blue["500"],
          "black": "#000000",
          "white": "#ffffff",
          "white-opacity70": "rgba(255, 255, 255, 0.7)",


        }
      }
    }
  })],
}
