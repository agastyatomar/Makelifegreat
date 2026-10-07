#!/usr/bin/env bash
set -euo pipefail
ROOT="$(pwd)"
TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT

echo "Fetching upstream implementation and transforming it into MakeLifeGreat..."
git clone --depth 1 https://github.com/karOS555/FocusForcePlus.git "$TMP/source"
rsync -a --exclude '.git' "$TMP/source/" "$ROOT/"

find . -type f -not -path './.git/*' -not -path './.github/*' -not -path './scripts/*' -not -path './customizations/*' -print0 | while IFS= read -r -d '' f; do
  if file -b --mime "$f" | grep -q 'charset='; then
    sed -i       -e 's/com\.focusforceplus\.app/com.aadi.makelifegreat/g'       -e 's/com\.focusforceplus/com.aadi/g'       -e 's/FocusForcePlus/MakeLifeGreat/g'       -e 's/FocusForce+/MakeLifeGreat/g'       -e 's/focusforceplus/makelifegreat/g'       -e 's/karOS555/agastyatomar/g'       -e 's/Aadi Tomar/Aadi/g' "$f" || true
  fi
done

# Overlay the Aadi-owned UI, logo, navigation and personal-life tools.
if [ -d customizations ]; then
  rsync -a customizations/ "$ROOT/"
fi

# Remove old upstream presentation assets from the generated source.
rm -rf docs/screenshots docs/index.html docs/social-preview.png docs/social-preview.svg   docs/feature-graphic.png docs/feature-graphic.svg docs/feature-graphic.webp   docs/logo.png docs/logo-192.png docs/og-image.png docs/sitemap.xml   docs/google0bc543ba4977aa24.html docs/robots.txt 2>/dev/null || true

if [ -f app/build.gradle.kts ]; then
  sed -i 's/namespace = "[^"]*"/namespace = "com.aadi.makelifegreat"/' app/build.gradle.kts
  sed -i 's/applicationId = "[^"]*"/applicationId = "com.aadi.makelifegreat"/' app/build.gradle.kts
fi
if [ -f settings.gradle.kts ]; then
  sed -i 's/rootProject.name = "[^"]*"/rootProject.name = "MakeLifeGreat"/' settings.gradle.kts
fi
echo "MakeLifeGreat source bootstrap complete."
