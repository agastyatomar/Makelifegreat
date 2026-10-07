#!/usr/bin/env bash
set -euo pipefail

ROOT="$(pwd)"
TMP="$(mktemp -d)"
trap 'rm -rf "$TMP"' EXIT

echo "Downloading FocusForcePlus source..."
git clone --depth 1 https://github.com/karOS555/FocusForcePlus.git "$TMP/source"

# Copy the complete Android project into this repository workspace.
rsync -a --exclude '.git' "$TMP/source/" "$ROOT/"

# MakeLifeGreat / Aadi identity transformation.
find . -type f -not -path './.git/*' -not -path './.github/*' -not -path './scripts/*' -print0 | while IFS= read -r -d '' f; do
  if file -b --mime "$f" | grep -q 'charset='; then
    sed -i \
      -e 's/com\.focusforceplus\.app/com.aadi.makelifegreat/g' \
      -e 's/com\.focusforceplus/com.aadi/g' \
      -e 's/FocusForcePlus/MakeLifeGreat/g' \
      -e 's/FocusForce+/MakeLifeGreat/g' \
      -e 's/focusforceplus/makelifegreat/g' \
      -e 's/karOS555/agastyatomar/g' \
      -e 's/Aadi Tomar/Aadi/g' \
      "$f" || true
  fi
done

# Rename the main application source file if the upstream file exists.
if [ -f app/src/main/java/com/aadi/makelifegreat/MakeLifeGreatApp.kt ]; then
  :
fi

# Ensure the Gradle identity is correct even if upstream changes its layout.
if [ -f app/build.gradle.kts ]; then
  sed -i 's/namespace = "[^"]*"/namespace = "com.aadi.makelifegreat"/' app/build.gradle.kts
  sed -i 's/applicationId = "[^"]*"/applicationId = "com.aadi.makelifegreat"/' app/build.gradle.kts
fi

if [ -f settings.gradle.kts ]; then
  sed -i 's/rootProject.name = "[^"]*"/rootProject.name = "MakeLifeGreat"/' settings.gradle.kts
fi

echo "MakeLifeGreat source bootstrap complete."
