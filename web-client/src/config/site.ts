export type SiteConfig = typeof siteConfig;

export const siteConfig = {
  name: "Learning Pulse",
  description: "A classroom with a quiz built in",
  navItems: [
    {
      label: "Home",
      href: "/",
    },
    {
      label: "Quiz",
      href: "/quiz"
    },
    {
      label: "Quiz Create",
      href: "/quiz-create"
    },
    {
      label: "Quiz Select",
      href: "/quiz-select"
    },   {
      label: "Quiz Answer Select",
      href: "/quiz-answer-select"
    },
    {
      label: "Quiz Grade",
      href: "/quiz-grade"
    },
    {
      label: "Logout",
      href: "/logout",
    },
  ],
  navMenuItems: [
    {
      label: "Profile",
      href: "/profile",
    },
    {
      label: "Dashboard",
      href: "/quiz",
    },
    {
      label: "Projects",
      href: "/projects",
    },
    {
      label: "Team",
      href: "/team",
    },
    {
      label: "Calendar",
      href: "/calendar",
    },
    {
      label: "Help & Feedback",
      href: "/help-feedback",
    },
    {
      label: "Logout",
      href: "/logout",
    },
  ],
  links: {
    github: "https://github.com/nextui-org/nextui",
    twitter: "https://twitter.com/getnextui",
    docs: "https://nextui.org",
    discord: "https://discord.gg/9b6yyZKmH4",
    sponsor: "https://patreon.com/jrgarciadev",
  },
};
