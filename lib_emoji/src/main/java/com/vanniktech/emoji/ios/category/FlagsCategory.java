package com.vanniktech.emoji.ios.category;


import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import com.vanniktech.emoji.R;
import com.vanniktech.emoji.emoji.EmojiCategory;
import com.vanniktech.emoji.ios.IosEmoji;

@SuppressWarnings("PMD.MethodReturnsInternalArray") public final class FlagsCategory implements EmojiCategory {
  private static final IosEmoji[] DATA = new IosEmoji[] {
    new IosEmoji(0x1F3C1, 9, 27, false),
    new IosEmoji(0x1F6A9, 35, 14, false),
    new IosEmoji(0x1F38C, 8, 31, false),
    new IosEmoji(0x1F3F4, 12, 19, false),
    new IosEmoji(new int[] { 0x1F3F3, 0xFE0F }, 12, 15, false),
    new IosEmoji(new int[] { 0x1F3F3, 0xFE0F, 0x200D, 0x1F308 }, 12, 14, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1E8 }, 0, 31, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1E9 }, 0, 32, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1EA }, 0, 33, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1EB }, 0, 34, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1EC }, 0, 35, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1EE }, 0, 36, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F1 }, 0, 37, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F2 }, 0, 38, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F4 }, 0, 39, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F6 }, 0, 40, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F7 }, 0, 41, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F8 }, 0, 42, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1F9 }, 0, 43, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1FA }, 0, 44, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1FC }, 0, 45, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1FD }, 0, 46, false),
    new IosEmoji(new int[] { 0x1F1E6, 0x1F1FF }, 0, 47, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1E6 }, 0, 48, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1E7 }, 0, 49, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1E9 }, 0, 50, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1EA }, 0, 51, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1EB }, 1, 0, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1EC }, 1, 1, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1ED }, 1, 2, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1EE }, 1, 3, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1EF }, 1, 4, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F1 }, 1, 5, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F2 }, 1, 6, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F3 }, 1, 7, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F4 }, 1, 8, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F6 }, 1, 9, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F7 }, 1, 10, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F8 }, 1, 11, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1F9 }, 1, 12, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1FB }, 1, 13, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1FC }, 1, 14, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1FE }, 1, 15, false),
    new IosEmoji(new int[] { 0x1F1E7, 0x1F1FF }, 1, 16, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1E6 }, 1, 17, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1E8 }, 1, 18, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1E9 }, 1, 19, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1EB }, 1, 20, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1EC }, 1, 21, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1ED }, 1, 22, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1EE }, 1, 23, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F0 }, 1, 24, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F1 }, 1, 25, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F2 }, 1, 26, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F3 }, 1, 27, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F4 }, 1, 28, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F5 }, 1, 29, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1F7 }, 1, 30, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FA }, 1, 31, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FB }, 1, 32, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FC }, 1, 33, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FD }, 1, 34, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FE }, 1, 35, false),
    new IosEmoji(new int[] { 0x1F1E8, 0x1F1FF }, 1, 36, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1EA }, 1, 37, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1EC }, 1, 38, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1EF }, 1, 39, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1F0 }, 1, 40, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1F2 }, 1, 41, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1F4 }, 1, 42, false),
    new IosEmoji(new int[] { 0x1F1E9, 0x1F1FF }, 1, 43, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1E6 }, 1, 44, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1E8 }, 1, 45, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1EA }, 1, 46, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1EC }, 1, 47, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1ED }, 1, 48, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1F7 }, 1, 49, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1F8 }, 1, 50, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1F9 }, 1, 51, false),
    new IosEmoji(new int[] { 0x1F1EA, 0x1F1FA }, 2, 0, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1EE }, 2, 1, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1EF }, 2, 2, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1F0 }, 2, 3, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1F2 }, 2, 4, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1F4 }, 2, 5, false),
    new IosEmoji(new int[] { 0x1F1EB, 0x1F1F7 }, 2, 6, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1E6 }, 2, 7, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1E7 }, 2, 8, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1E9 }, 2, 9, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1EA }, 2, 10, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1EB }, 2, 11, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1EC }, 2, 12, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1ED }, 2, 13, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1EE }, 2, 14, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F1 }, 2, 15, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F2 }, 2, 16, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F3 }, 2, 17, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F5 }, 2, 18, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F6 }, 2, 19, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F7 }, 2, 20, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F8 }, 2, 21, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1F9 }, 2, 22, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1FA }, 2, 23, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1FC }, 2, 24, false),
    new IosEmoji(new int[] { 0x1F1EC, 0x1F1FE }, 2, 25, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1F0 }, 2, 26, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1F2 }, 2, 27, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1F3 }, 2, 28, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1F7 }, 2, 29, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1F9 }, 2, 30, false),
    new IosEmoji(new int[] { 0x1F1ED, 0x1F1FA }, 2, 31, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1E8 }, 2, 32, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1E9 }, 2, 33, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1EA }, 2, 34, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F1 }, 2, 35, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F2 }, 2, 36, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F3 }, 2, 37, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F4 }, 2, 38, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F6 }, 2, 39, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F7 }, 2, 40, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F8 }, 2, 41, false),
    new IosEmoji(new int[] { 0x1F1EE, 0x1F1F9 }, 2, 42, false),
    new IosEmoji(new int[] { 0x1F1EF, 0x1F1EA }, 2, 43, false),
    new IosEmoji(new int[] { 0x1F1EF, 0x1F1F2 }, 2, 44, false),
    new IosEmoji(new int[] { 0x1F1EF, 0x1F1F4 }, 2, 45, false),
    new IosEmoji(new int[] { 0x1F1EF, 0x1F1F5 }, 2, 46, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1EA }, 2, 47, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1EC }, 2, 48, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1ED }, 2, 49, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1EE }, 2, 50, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1F2 }, 2, 51, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1F3 }, 3, 0, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1F5 }, 3, 1, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1F7 }, 3, 2, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1FC }, 3, 3, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1FE }, 3, 4, false),
    new IosEmoji(new int[] { 0x1F1F0, 0x1F1FF }, 3, 5, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1E6 }, 3, 6, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1E7 }, 3, 7, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1E8 }, 3, 8, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1EE }, 3, 9, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1F0 }, 3, 10, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1F7 }, 3, 11, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1F8 }, 3, 12, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1F9 }, 3, 13, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1FA }, 3, 14, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1FB }, 3, 15, false),
    new IosEmoji(new int[] { 0x1F1F1, 0x1F1FE }, 3, 16, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1E6 }, 3, 17, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1E8 }, 3, 18, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1E9 }, 3, 19, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1EA }, 3, 20, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1EB }, 3, 21, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1EC }, 3, 22, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1ED }, 3, 23, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F0 }, 3, 24, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F1 }, 3, 25, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F2 }, 3, 26, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F3 }, 3, 27, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F4 }, 3, 28, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F5 }, 3, 29, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F6 }, 3, 30, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F7 }, 3, 31, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F8 }, 3, 32, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1F9 }, 3, 33, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FA }, 3, 34, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FB }, 3, 35, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FC }, 3, 36, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FD }, 3, 37, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FE }, 3, 38, false),
    new IosEmoji(new int[] { 0x1F1F2, 0x1F1FF }, 3, 39, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1E6 }, 3, 40, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1E8 }, 3, 41, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1EA }, 3, 42, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1EB }, 3, 43, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1EC }, 3, 44, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1EE }, 3, 45, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1F1 }, 3, 46, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1F4 }, 3, 47, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1F5 }, 3, 48, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1F7 }, 3, 49, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1FA }, 3, 50, false),
    new IosEmoji(new int[] { 0x1F1F3, 0x1F1FF }, 3, 51, false),
    new IosEmoji(new int[] { 0x1F1F4, 0x1F1F2 }, 4, 0, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1E6 }, 4, 1, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1EA }, 4, 2, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1EB }, 4, 3, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1EC }, 4, 4, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1ED }, 4, 5, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F0 }, 4, 6, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F1 }, 4, 7, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F2 }, 4, 8, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F3 }, 4, 9, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F7 }, 4, 10, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F8 }, 4, 11, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1F9 }, 4, 12, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1FC }, 4, 13, false),
    new IosEmoji(new int[] { 0x1F1F5, 0x1F1FE }, 4, 14, false),
    new IosEmoji(new int[] { 0x1F1F6, 0x1F1E6 }, 4, 15, false),
    new IosEmoji(new int[] { 0x1F1F7, 0x1F1EA }, 4, 16, false),
    new IosEmoji(new int[] { 0x1F1F7, 0x1F1F4 }, 4, 17, false),
    new IosEmoji(new int[] { 0x1F1F7, 0x1F1F8 }, 4, 18, false),
    new IosEmoji(new int[] { 0x1F1F7, 0x1F1FA }, 4, 19, false),
    new IosEmoji(new int[] { 0x1F1F7, 0x1F1FC }, 4, 20, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1E6 }, 4, 21, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1E7 }, 4, 22, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1E8 }, 4, 23, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1E9 }, 4, 24, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1EA }, 4, 25, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1EC }, 4, 26, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1ED }, 4, 27, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1EE }, 4, 28, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1EF }, 4, 29, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F0 }, 4, 30, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F1 }, 4, 31, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F2 }, 4, 32, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F3 }, 4, 33, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F4 }, 4, 34, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F7 }, 4, 35, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F8 }, 4, 36, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1F9 }, 4, 37, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1FB }, 4, 38, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1FD }, 4, 39, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1FE }, 4, 40, false),
    new IosEmoji(new int[] { 0x1F1F8, 0x1F1FF }, 4, 41, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1E6 }, 4, 42, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1E8 }, 4, 43, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1E9 }, 4, 44, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1EB }, 4, 45, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1EC }, 4, 46, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1ED }, 4, 47, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1EF }, 4, 48, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F0 }, 4, 49, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F1 }, 4, 50, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F2 }, 4, 51, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F3 }, 5, 0, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F4 }, 5, 1, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F7 }, 5, 2, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1F9 }, 5, 3, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1FB }, 5, 4, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1FC }, 5, 5, false),
    new IosEmoji(new int[] { 0x1F1F9, 0x1F1FF }, 5, 6, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1E6 }, 5, 7, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1EC }, 5, 8, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1F2 }, 5, 9, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1F8 }, 5, 11, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1FE }, 5, 12, false),
    new IosEmoji(new int[] { 0x1F1FA, 0x1F1FF }, 5, 13, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1E6 }, 5, 14, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1E8 }, 5, 15, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1EA }, 5, 16, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1EC }, 5, 17, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1EE }, 5, 18, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1F3 }, 5, 19, false),
    new IosEmoji(new int[] { 0x1F1FB, 0x1F1FA }, 5, 20, false),
    new IosEmoji(new int[] { 0x1F1FC, 0x1F1EB }, 5, 21, false),
    new IosEmoji(new int[] { 0x1F1FC, 0x1F1F8 }, 5, 22, false),
    new IosEmoji(new int[] { 0x1F1FD, 0x1F1F0 }, 5, 23, false),
    new IosEmoji(new int[] { 0x1F1FE, 0x1F1EA }, 5, 24, false),
    new IosEmoji(new int[] { 0x1F1FE, 0x1F1F9 }, 5, 25, false),
    new IosEmoji(new int[] { 0x1F1FF, 0x1F1E6 }, 5, 26, false),
    new IosEmoji(new int[] { 0x1F1FF, 0x1F1F2 }, 5, 27, false),
    new IosEmoji(new int[] { 0x1F1FF, 0x1F1FC }, 5, 28, false),
    new IosEmoji(new int[] { 0x1F3F4, 0xE0067, 0xE0062, 0xE0065, 0xE006E, 0xE0067, 0xE007F }, 12, 16, false),
    new IosEmoji(new int[] { 0x1F3F4, 0xE0067, 0xE0062, 0xE0073, 0xE0063, 0xE0074, 0xE007F }, 12, 17, false),
    new IosEmoji(new int[] { 0x1F3F4, 0xE0067, 0xE0062, 0xE0077, 0xE006C, 0xE0073, 0xE007F }, 12, 18, false)
  };

  @Override @NonNull
  public IosEmoji[] getEmojis() {
    return DATA;
  }

  @Override @DrawableRes
  public int getIcon() {
    return R.drawable.emoji_ios_category_flags;
  }
}